package study.template.composable

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

inline class AppInfoData(val value: String)

@Composable
fun AppInfo(data: AppInfoData) {
    MaterialTheme {
        Column(modifier = Spacing(16.dp)) {
            Text(text = data.value)
        }
    }
}

@[Preview Composable]
fun PreviewAppInfo() = AppInfo(AppInfoData("An app info"))
