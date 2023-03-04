/*
 * Copyright (C) 2023 The Android Open Source Project
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
 *
 */

package com.android.customization.picker.clock.domain.interactor

import com.android.customization.picker.clock.data.repository.ClockPickerRepository
import com.android.customization.picker.clock.shared.ClockSize
import com.android.customization.picker.clock.shared.model.ClockMetadataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/**
 * Interactor for accessing application clock settings, as well as selecting and configuring custom
 * clocks.
 */
class ClockPickerInteractor(private val repository: ClockPickerRepository) {

    val allClocks: Flow<List<ClockMetadataModel>> = repository.allClocks

    val selectedClockId: Flow<String> =
        repository.selectedClock.map { clock -> clock.clockId }.distinctUntilChanged()

    val selectedColor: Flow<Int?> =
        repository.selectedClock.map { clock -> clock.selectedColor }.distinctUntilChanged()

    val colorTone: Flow<Int> = repository.selectedClock.map { clock -> clock.colorTone }

    val seedColor: Flow<Int?> = repository.selectedClock.map { clock -> clock.seedColor }

    val selectedClockSize: Flow<ClockSize> = repository.selectedClockSize

    fun setSelectedClock(clockId: String) {
        repository.setSelectedClock(clockId)
    }

    fun setClockColor(selectedColor: Int?, colorTone: Int, seedColor: Int?) {
        repository.setClockColor(selectedColor, colorTone, seedColor)
    }

    suspend fun setClockSize(size: ClockSize) {
        repository.setClockSize(size)
    }
}
