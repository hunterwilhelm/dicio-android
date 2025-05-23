package org.stypox.dicio.skills.bluetooth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.dicio.skill.context.SkillContext
import org.dicio.skill.skill.SkillOutput
import org.stypox.dicio.R
import org.stypox.dicio.io.graphical.Headline
import org.stypox.dicio.util.getString

sealed interface BluetoothOutput : SkillOutput {
    data class Success(
        val isEnabled: Boolean,
        val isAction: Boolean,
    ) : BluetoothOutput {
        override fun getSpeechOutput(ctx: SkillContext): String = when {
            isAction && isEnabled -> ctx.getString(R.string.skill_bluetooth_enabled)
            isAction && !isEnabled -> ctx.getString(R.string.skill_bluetooth_disabled)
            isEnabled -> ctx.getString(R.string.skill_bluetooth_is_enabled)
            else -> ctx.getString(R.string.skill_bluetooth_is_disabled)
        }

        @Composable
        override fun GraphicalOutput(ctx: SkillContext) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = if (isEnabled) Icons.Default.Bluetooth else Icons.Default.BluetoothDisabled,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                
                Text(
                    text = getSpeechOutput(ctx),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }

    data class Error(
        val errorMessage: String,
    ) : BluetoothOutput {
        override fun getSpeechOutput(ctx: SkillContext): String =
            ctx.getString(R.string.skill_bluetooth_error, errorMessage)

        @Composable
        override fun GraphicalOutput(ctx: SkillContext) {
            Headline(text = getSpeechOutput(ctx))
        }
    }
}
