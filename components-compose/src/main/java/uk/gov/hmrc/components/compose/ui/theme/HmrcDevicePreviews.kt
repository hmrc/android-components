/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.ptcalc.common.compose.core

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Device previews for light and dark mode
 */
@Preview(
    name = "Phone - Light",
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Phone - Dark",
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
@Preview(
    name = "Tablet - Light",
    device = Devices.PIXEL_C,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Tablet - Dark",
    device = Devices.PIXEL_C,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
annotation class HmrcAllDevicePreview

/**
 * Only phone previews for light and dark mode
 */
@Preview(
    name = "Phone - Light",
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Phone - Dark",
    device = Devices.PIXEL_4,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
@Preview(
    name = "Phone - Dark",
    device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
annotation class HmrcPhonePreview

/**
 * Only Tab previews for light and dark mode
 */
@Preview(
    name = "Tablet - Light",
    device = Devices.PIXEL_C,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Preview(
    name = "Tablet - Dark",
    device = Devices.PIXEL_C,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
annotation class HmrcTabPreview

@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
annotation class LightModePreview

@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
    showBackground = true,
)
annotation class DarkModePreview
