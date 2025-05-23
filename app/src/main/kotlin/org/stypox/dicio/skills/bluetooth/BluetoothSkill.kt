package org.stypox.dicio.skills.bluetooth

import android.bluetooth.BluetoothAdapter
import org.dicio.skill.context.SkillContext
import org.dicio.skill.skill.SkillInfo
import org.dicio.skill.skill.SkillOutput
import org.dicio.skill.standard.StandardRecognizerData
import org.dicio.skill.standard.StandardRecognizerSkill
import org.stypox.dicio.sentences.Sentences.Bluetooth

class BluetoothSkill(correspondingSkillInfo: SkillInfo, data: StandardRecognizerData<Bluetooth>)
    : StandardRecognizerSkill<Bluetooth>(correspondingSkillInfo, data) {

    override suspend fun generateOutput(ctx: SkillContext, inputData: Bluetooth): SkillOutput {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            return BluetoothOutput.Error("Bluetooth is not supported on this device")
        }

        // The permission check is now handled by the app's permission system
        // through the neededPermissions property in BluetoothInfo

        return when (inputData) {
            is Bluetooth.Enable -> {
                val success = bluetoothAdapter.enable()
                BluetoothOutput.Success(isEnabled = true, isAction = true)
            }
            is Bluetooth.Disable -> {
                val success = bluetoothAdapter.disable()
                BluetoothOutput.Success(isEnabled = false, isAction = true)
            }
            is Bluetooth.Status -> {
                val isEnabled = bluetoothAdapter.isEnabled
                BluetoothOutput.Success(isEnabled = isEnabled, isAction = false)
            }
        }
    }
}
