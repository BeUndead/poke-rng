package com.com.poke.search.compute;

import com.com.poke.model.AbilitySlot;
import com.com.poke.model.Ball;
import com.com.poke.model.BreedingItems;
import com.com.poke.model.Gender;
import com.com.poke.model.IVs;
import com.com.poke.model.Nature;
import com.com.poke.search.context.FilterContext;
import com.com.poke.search.context.ParentsContext;
import com.com.poke.search.context.SearchContext;
import com.com.poke.search.context.TrainerContext;
import com.com.rng.mt.MTRng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.com.poke.search.compute.Calculators.abilitySlotCalculator;
import static com.com.poke.search.compute.Calculators.ballCalculator;
import static com.com.poke.search.compute.Calculators.encryptionConstantCalculator;
import static com.com.poke.search.compute.Calculators.everstoneNatureCalculator;
import static com.com.poke.search.compute.Calculators.genderCalculator;
import static com.com.poke.search.compute.Calculators.ivsCalculator;
import static com.com.poke.search.compute.Calculators.ivsInheritanceCalculator;
import static com.com.poke.search.compute.Calculators.pidCalculator;
import static com.com.poke.search.compute.Calculators.uninheritedNatureCalculator;


/**
 * Implementation of {@link FilteredOffspringSpreadCalculator} for {@code Generation 7} {@code Pokémon} breeding.
 */
public final class Gen7FilteredOffspringSpreadCalculator implements FilteredOffspringSpreadCalculator {

    /**
     * {@link ExecutorService} so that we may perform multi-threaded searches.
     */
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(100);

    private final boolean performFiltering;

    public Gen7FilteredOffspringSpreadCalculator(final boolean performFiltering) {

        this.performFiltering = performFiltering;
    }

    public Gen7FilteredOffspringSpreadCalculator() {

        this(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<SearchResult> performSearch(
            final ParentsContext parentsContext,
            final TrainerContext trainerContext,
            final SearchContext searchContext,
            final FilterContext filterContext) {

        final List<SearchResult> results = Collections.synchronizedList(new ArrayList<>());
        final MTRng rng = searchContext.rng();

        // Skip frames to the initial value.
        long frame = 0;
        for (; frame < searchContext.initialFrame(); frame++) {
            rng.nextInt();
        }

        // Keep track of the Futures to ensure all have finished before returning results.
        final List<Future<?>> spreadCheckers = new ArrayList<>();
        for (; frame < searchContext.numberOfFrames() + searchContext.initialFrame(); frame++, rng.nextInt()) {
            final MTRng rngClone = rng.cloneAndResetAdvancements();

            final int[] initialSeed = rng.state();

            final long finalFrameCopy = frame;
            // Submit to threadPool.
            spreadCheckers.add(threadPool.submit(() -> {

                final Gender gender = genderCalculator().calculate(parentsContext, trainerContext, rngClone);
                if (performFiltering && !filterContext.genderFilter().isAcceptable(gender)) {
                    return;
                }

                final Nature initialNature =
                        uninheritedNatureCalculator().calculate(parentsContext, trainerContext, rngClone);

                final AbilitySlot abilitySlot =
                        abilitySlotCalculator().calculate(parentsContext, trainerContext, rngClone);
                if (performFiltering && !filterContext.abilitySlotFilter().isAcceptable(abilitySlot)) {
                    return;
                }

                final InheritanceType<Nature> natureInheritanceType;
                final Nature nature;
                if (parentsContext.maleContext().heldItem() != BreedingItems.Everstone
                        && parentsContext.femaleContext().heldItem() != BreedingItems.Everstone) {
                    natureInheritanceType = NatureInheritanceType.NotInherited;
                    nature = initialNature;
                } else {
                    natureInheritanceType = everstoneNatureCalculator().calculateInheritaceType(parentsContext, rng);
                    nature = natureInheritanceType.determineInheritance(parentsContext);

                }
                if (performFiltering && !filterContext.natureFilter().isAcceptable(nature)) {
                    return;
                }

                final IVsInheritanceType ivsInheritance =
                        (IVsInheritanceType) ivsInheritanceCalculator().calculateInheritaceType(parentsContext,
                                rngClone);
                final IVs ivs = ivsCalculator().calculate(parentsContext, ivsInheritance);
                if (performFiltering && !filterContext.ivsFilter().isAcceptable(ivs)) {
                    return;
                }
                if (performFiltering && !filterContext.hiddenPowerTypeFilter().isAcceptable(ivs.hiddenPower())) {
                    return;
                }

                final int encryptionConstant =
                        encryptionConstantCalculator().calculate(parentsContext, trainerContext, rngClone);

                final Integer pid = pidCalculator().calculate(parentsContext, trainerContext, rngClone);
                final Integer shinyValue = pid == null ? null : ((pid >>> 0x10) ^ (pid & 0xffff)) >>> 4;
                if (performFiltering && !filterContext.shinyValueFilter().isAcceptable(shinyValue)) {
                    return;
                }

                final InheritanceType<Ball>
                        ballInheritanceType = ballCalculator().calculateInheritaceType(parentsContext, rngClone);
                final Ball ball = ballInheritanceType.determineInheritance(parentsContext);
                if (performFiltering && !filterContext.ballFilter().isAcceptable(ball)) {
                    return;
                }

                final OffspringSpread offspringSpread = new SimpleOffspringSpread(
                        abilitySlot,
                        ballInheritanceType,
                        ball,
                        gender,
                        ivsInheritance,
                        ivs,
                        natureInheritanceType,
                        nature,
                        pid,
                        encryptionConstant);

                // Note: 2 additional advancements are always consumed.
                results.add(new SimpleSearchResult(
                        offspringSpread, initialSeed, finalFrameCopy, rngClone.advancements() + 2));
            }));
        }

        for (final Future<?> spreadChecker : spreadCheckers) {
            try {
                spreadChecker.get();
            } catch (final InterruptedException interruptedException) {
                threadPool.shutdown();
                throw new IllegalStateException("Interruption accepted", interruptedException);
            } catch (final ExecutionException executionException) {
                return Collections.emptyList();
            }
        }

        // Sort by the frameFromInitial to ensure that they come out in a logical order.
        results.sort(Comparator.comparingLong(SearchResult::frameFromInitial));
        return results;
    }
}
