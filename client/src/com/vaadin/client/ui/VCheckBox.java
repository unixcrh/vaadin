/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.client.ui;

import com.google.gwt.aria.client.CheckedValue;
import com.google.gwt.aria.client.Roles;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.Util;
import com.vaadin.client.VTooltip;

public class VCheckBox extends com.google.gwt.user.client.ui.CheckBox implements
        Field {

    public static final String CLASSNAME = "v-checkbox";

    /** For internal use only. May be removed or replaced in the future. */
    public String id;

    /** For internal use only. May be removed or replaced in the future. */
    public boolean immediate;

    /** For internal use only. May be removed or replaced in the future. */
    public ApplicationConnection client;

    /** For internal use only. May be removed or replaced in the future. */
    public Element errorIndicatorElement;

    /** For internal use only. May be removed or replaced in the future. */
    public Icon icon;

    public VCheckBox() {
        setStyleName(CLASSNAME);

        // Add a11y role "checkbox"
        Roles.getCheckboxRole().set(getElement());
        Roles.getCheckboxRole().setAriaCheckedState(getElement(),
                CheckedValue.FALSE);

        Element el = DOM.getFirstChild(getElement());
        while (el != null) {
            DOM.sinkEvents(el,
                    (DOM.getEventsSunk(el) | VTooltip.TOOLTIP_EVENTS));
            el = DOM.getNextSibling(el);
        }
    }

    @Override
    public void onBrowserEvent(Event event) {
        if (icon != null && (event.getTypeInt() == Event.ONCLICK)
                && (DOM.eventGetTarget(event) == icon.getElement())) {
            // Click on icon should do nothing if widget is disabled
            if (isEnabled()) {
                setValue(!getValue());
            }
        }
        super.onBrowserEvent(event);
        if (event.getTypeInt() == Event.ONLOAD) {
            Util.notifyParentOfSizeChange(this, true);
        }
    }

    @Override
    public void setValue(Boolean value, boolean fireEvents) {
        setCheckedValue(value);
        super.setValue(value, fireEvents);
    }

    private void setCheckedValue(Boolean value) {
        CheckedValue checkedValue = value ? CheckedValue.TRUE
                : CheckedValue.FALSE;
        Roles.getCheckboxRole().setAriaCheckedState(getElement(), checkedValue);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        Roles.getCheckboxRole().setAriaDisabledState(getElement(), true);
    }
}
