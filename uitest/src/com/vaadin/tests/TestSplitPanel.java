/* 
 * Copyright 2011 Vaadin Ltd.
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

package com.vaadin.tests;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI.LegacyWindow;
import com.vaadin.ui.VerticalSplitPanel;

public class TestSplitPanel extends com.vaadin.Application {

    VerticalSplitPanel verticalSplit = new VerticalSplitPanel();

    @Override
    public void init() {
        final LegacyWindow mainWindow = new LegacyWindow("Feature Browser");
        setMainWindow(mainWindow);

        verticalSplit.setFirstComponent(new Label("vertical first"));
        verticalSplit.setSecondComponent(new Label("vertical second"));

        mainWindow.setContent(verticalSplit);

    }

}