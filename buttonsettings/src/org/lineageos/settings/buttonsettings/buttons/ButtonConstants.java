/*
 * Copyright (C) 2016 The CyanogenMod Project
 *               2017-2018,2021 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.buttonsettings.buttons;

import org.lineageos.settings.buttonsettings.util.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class ButtonConstants {

    // Preference keys
    public static final String BUTTON_SWAP_KEY = "button_swap";
    public static final String FP_HOME_KEY = "fp_home";
    public static final String FP_POCKETMODE_KEY = "fp_pocketmode";
    public static final String FP_WAKEUP_KEY = "fp_wakeup";

    // Nodes
    public static final String CYTTSP_BUTTON_SWAP_NODE = "/sys/devices/soc/75ba000.i2c/i2c-12/12-004A/reversed_keys";
    public static final String FP_HOME_KEY_NODE = "/sys/devices/soc/soc:fpc_fpc1020/enable_key_events";
    public static final String FP_PROX_NODE = "/sys/devices/soc/soc:fpc_fpc1020/proximity_state";
    public static final String FP_WAKEUP_NODE = "/sys/devices/soc/soc:fpc_fpc1020/enable_wakeup";
    public static final String TOUCHPANEL_BUTTON_SWAP_NODE = "/sys/devices/soc/75ba000.i2c/i2c-12/12-004A/reversed_keys";
    public static final String VIRTUAL_KEYS_NODE = "/sys/devices/soc/75ba000.i2c/i2c-12/12-0020/input/input1/0dbutton";

    // Holds <preference_key> -> <proc_node> mapping
    public static final Map<String, String> sBooleanNodePreferenceMap = new HashMap<>();

    // Holds <preference_key> -> <default_values> mapping
    public static final Map<String, Object> sNodeDefaultMap = new HashMap<>();

    // Holds <preference_key> -> <user_set_values> mapping
    public static final Map<String, Object[]> sNodeUserSetValuesMap = new HashMap<>();

    // Holds <preference_key> -> <dependency_check> mapping
    public static final Map<String, String[]> sNodeDependencyMap = new HashMap<>();

    public static final String[] sButtonPrefKeys = {
        BUTTON_SWAP_KEY,
        FP_HOME_KEY,
        FP_WAKEUP_KEY
    };

    static {
        if (FileUtils.fileExists(CYTTSP_BUTTON_SWAP_NODE)) {
            sBooleanNodePreferenceMap.put(BUTTON_SWAP_KEY, CYTTSP_BUTTON_SWAP_NODE);
        } else if (FileUtils.fileExists(TOUCHPANEL_BUTTON_SWAP_NODE)) {
            sBooleanNodePreferenceMap.put(BUTTON_SWAP_KEY, TOUCHPANEL_BUTTON_SWAP_NODE);
        }
        sBooleanNodePreferenceMap.put(FP_HOME_KEY, FP_HOME_KEY_NODE);
        sBooleanNodePreferenceMap.put(FP_WAKEUP_KEY, FP_WAKEUP_NODE);

        sNodeDefaultMap.put(BUTTON_SWAP_KEY, false);
        sNodeDefaultMap.put(FP_HOME_KEY, false);
        sNodeDefaultMap.put(FP_WAKEUP_KEY, true);

        sNodeDependencyMap.put(FP_HOME_KEY, new String[]{ VIRTUAL_KEYS_NODE, "1" });
    }
}
