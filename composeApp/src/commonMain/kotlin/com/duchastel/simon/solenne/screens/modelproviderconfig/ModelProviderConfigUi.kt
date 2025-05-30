package com.duchastel.simon.solenne.screens.modelproviderconfig

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import solennechatapp.composeapp.generated.resources.Res
import solennechatapp.composeapp.generated.resources.api_key_label
import solennechatapp.composeapp.generated.resources.save_button
import solennechatapp.composeapp.generated.resources.screen_title_configure_provider
import com.duchastel.simon.solenne.screens.modelproviderconfig.ModelProviderConfigScreen.Event
import com.duchastel.simon.solenne.screens.modelproviderconfig.ModelProviderConfigScreen.State
import com.duchastel.simon.solenne.screens.modelproviderselector.UiModelProvider
import com.duchastel.simon.solenne.ui.components.SolenneScaffold
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ModelProviderConfigUi(
    state: State,
    modifier: Modifier = Modifier,
) {
    val eventSink = state.eventSink

    SolenneScaffold(
        title = stringResource(Res.string.screen_title_configure_provider, state.modelProvider),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.apiKey ?: "",
                onValueChange = { eventSink(Event.ApiKeyChanged(it)) },
                label = { Text(stringResource(Res.string.api_key_label)) },
                isError = state.apiKey?.isBlank() == true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { eventSink(Event.SavePressed) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(Res.string.save_button))
            }
        }
    }
}

@Preview
@Composable
internal fun ModelProviderConfigUi_Preview() {
    ModelProviderConfigUi(
        state = State(
            apiKey = "sk-1234567890",
            modelProvider = UiModelProvider.Gemini,
        )
    )
}
