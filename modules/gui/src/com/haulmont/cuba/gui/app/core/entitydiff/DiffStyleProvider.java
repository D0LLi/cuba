/*
 * Copyright (c) 2011 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.
 */

package com.haulmont.cuba.gui.app.core.entitydiff;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.EntityBasicPropertyDiff;
import com.haulmont.cuba.core.global.EntityClassPropertyDiff;
import com.haulmont.cuba.core.global.EntityCollectionPropertyDiff;
import com.haulmont.cuba.core.global.EntityPropertyDiff;
import com.haulmont.cuba.gui.components.Table;

/**
 * Set icons and colors for EntityDiff UI
 * <p>$Id$</p>
 *
 * @author artamonov
 */
public class DiffStyleProvider implements Table.StyleProvider {

    @Override
    public String getStyleName(Entity entity, String property) {
        if (property != null) {
            if ("name".equals(property)) {
                if (entity instanceof EntityClassPropertyDiff) {
                    switch (((EntityPropertyDiff) entity).getItemState()) {
                        case Added:
                            return "addedItem";

                        case Modified:
                            return "modifiedItem";

                        case Normal:
                            if (((EntityClassPropertyDiff) entity).isLinkChange())
                                return "chainItem";
                            else
                                return "modifiedItem";

                        case Removed:
                            return "removedItem";
                    }
                } else if (entity instanceof EntityCollectionPropertyDiff) {
                    return "categoryItem";
                } else if (entity instanceof EntityBasicPropertyDiff) {
                    //return "modifiedItem";
                }
            }
        }
        return null;
    }

    @Override
    public String getItemIcon(Entity entity) {
        return null;
    }
}
