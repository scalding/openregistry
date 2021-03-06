/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.openregistry.core.domain.sor;

import java.util.Collection;
import java.util.Map;

/**
 * Represents a System of Record's specification.
 *
 * @version $Revision$ $Date$
 * @since 0.1
 */
public interface SoRSpecification {

    enum Interfaces {BATCH, HUMAN, REALTIME}

    /**
     * Returns the String representation of the SoR who this specification is for.
     *
     * @return the String representation.  CANNOT be NULL.
     */
    String getSoR();

    /**
     * Returns the name of the SoR.  This can be a longer description (i.e. Continuing Education).
     * @return the name, cannot be null.
     */
    String getName();

    /**
     * The description of the specification.  What its goals are, what it hopes to accomplish, etc.
     *
     * @return the description.  CANNOT be NULL.
     */
    String getDescription();

    /**
     * Determines where a System of Record is allowed to submit data via this interface.
     *
     * @param interfaces the interface to check.
     * @return true if it can, false otherwise.
     */
    boolean isInboundInterfaceAllowed(Interfaces interfaces);

    /**
     * Returns the notification scheme that should be used based on the interface provided.
     * <p>
     * There should be no interfaces in this mapping that do not satisfy the requirement of
     * {@link org.openregistry.core.domain.sor.SoRSpecification#isInboundInterfaceAllowed(Interfaces)}
     * == true.
     *
     * @return the map containing the mappings.
     */
    Map<Interfaces, String> getNotificationSchemesByInterface();

    /**
     * Determines whether an SoR is allowed to assert a specific type value for a property.
     * <p>
     * Note, if the property is not found in the SoR's specification, the assumption is that there is no
     * restriction.
     * <p>
     * If a property is disallowed, this should return false.
     *
     * @param property the property to check
     * @param value the value we want to confirm.
     * @return true, if its allowed, false otherwise.
     */
    boolean isAllowedValueForProperty(String property, String value);

    /**
     * Determines whether an SoR is required to send this property or not.
     *
     * @param property the property to check
     * @return true if its required, false if not.
     */
    boolean isRequiredProperty(String property);

    /**
     * Determines whether an SoR is allowed to send this property or not.
     *
     * @param property the property to check
     * @return true if its disallowed, false otherwise.
     */
    boolean isDisallowedProperty(String property);

    /**
     * Determines whether an SoR provided a collection of objects of the appropriate size.
     * <p>
     * Note, that if the property is not found in the SoR's specification, the assumption is that there
     * is no restriction.
     * @param property the property to check
     * @param collection the collection to determine the size of
     * @return true if its the required size, false if not.
     */
    boolean isWithinRequiredSize(String property, Collection collection);

    /**
     * Determines whether an SoR provided a collection of objects of the appropriate size.
     * <p>
     * Note, that if the property is not found in the SoR's specification, the assumption is that there
     * is no restriction.
     * @param property the property to check
     * @param map the map to determine the size of
     * @return true if its the required size, false if not.
     */
    boolean isWithinRequiredSize(String property, Map map);

}
