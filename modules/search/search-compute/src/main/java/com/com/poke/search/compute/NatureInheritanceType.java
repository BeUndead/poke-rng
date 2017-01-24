package com.com.poke.search.compute;

import com.com.poke.model.Nature;
import com.com.poke.search.context.ParentsContext;

/**
 * {@link InheritanceType} implementation for {@link Nature}.
 */
enum NatureInheritanceType implements InheritanceType<Nature> {

    /**
     * The {@link Nature} is to be {@link InheritanceType inherited} from the {@link com.com.poke.model.Genders#Male}
     * parent.
     */
    Male {
        /**
         * {@inheritDoc}
         */
        @Override
        public Nature determineInheritance(final ParentsContext parentsContext) {

            return parentsContext.maleContext().nature();
        }
    },

    /**
     * The {@link Nature} is to be {@link InheritanceType inherited} from the {@link com.com.poke.model.Genders#Female}
     * parent.
     */
    Female {
        /**
         * {@inheritDoc}
         */
        @Override
        public Nature determineInheritance(final ParentsContext parentsContext) {

            return parentsContext.femaleContext().nature();
        }
    },

    /**
     * The {@link Nature} is <strong>not</strong> inherited.
     */
    NotInherited {
        /**
         * {@inheritDoc}
         * <p>
         * Implementation returns {@code null} to indicate that the {@link Nature} cannot be determined from the
         * given information.
         */
        @Override
        public Nature determineInheritance(final ParentsContext parentsContext) {

            return null;
        }
    }
}
