package org.stypox.dicio.skills.bluetooth

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import org.dicio.skill.context.SkillContext
import org.dicio.skill.skill.Permission
import org.dicio.skill.skill.Skill
import org.dicio.skill.skill.SkillInfo
import org.stypox.dicio.R
import org.stypox.dicio.sentences.Sentences
import org.stypox.dicio.util.PERMISSION_BLUETOOTH_ADMIN

object BluetoothInfo : SkillInfo("bluetooth") {
    override fun name(context: Context) =
        context.getString(R.string.skill_name_bluetooth)

    override fun sentenceExample(context: Context) =
        context.getString(R.string.skill_sentence_example_bluetooth)

    @Composable
    override fun icon() =
        rememberVectorPainter(Icons.Default.Bluetooth)

    override val neededPermissions: List<Permission>
            = listOf(PERMISSION_BLUETOOTH_ADMIN)

    override fun isAvailable(ctx: SkillContext): Boolean {
        return Sentences.Bluetooth[ctx.sentencesLanguage] != null
    }

    override fun build(ctx: SkillContext): Skill<*> {
        return BluetoothSkill(BluetoothInfo, Sentences.Bluetooth[ctx.sentencesLanguage]!!)
    }
}
