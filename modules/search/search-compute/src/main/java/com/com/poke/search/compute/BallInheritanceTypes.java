package com.com.poke.search.compute;

import com.com.poke.model.Ball;
import com.com.poke.model.Balls;
import com.com.poke.search.context.ParentsContext;

/**
 * The various manners in which the {@link Ball} may be {@link InheritanceType inherited}.
 */
enum BallInheritanceTypes implements InheritanceType<Ball> {

    /**
     * {@link Ball} {@link InheritanceType inherited} from the {@link com.com.poke.model.Genders#Male} parent.
     */
    Male {
        /**
         * {@inheritDoc}
         */
        @Override
        public Ball determineInheritance(final ParentsContext parentsContext) {

            final Ball ball = parentsContext.maleContext().ball();
            if (ball.canBeInherited()) {
                return ball;
            }
            return Balls.Poke;
        }
    },

    /**
     * {@link Ball} {@link InheritanceType inherited} from the {@link com.com.poke.model.Genders#Female} parent.
     */
    Female {
        /**
         * {@inheritDoc}
         */
        @Override
        public Ball determineInheritance(final ParentsContext parentsContext) {

            final Ball ball = parentsContext.femaleContext().ball();
            if (ball.canBeInherited()) {
                return ball;
            }
            return Balls.Poke;
        }
    },

    /**
     * {@link Ball} {@link InheritanceType inherited} from the neither parent.
     */
    NotInherited {
        /**
         * {@inheritDoc}
         */
        @Override
        public Ball determineInheritance(final ParentsContext parentsContext) {

            return Balls.Poke;
        }
    }
}
