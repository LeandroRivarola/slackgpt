# slackgpt
Proyecto que envia los mensajes de un canal de slack a chat gpt para que los resuma utilizando las respectivas APIS.

Para utilizarlo se deben establecer las variables de entorno:

Establece que modelo de chat gpt utilizar. openai.model=gpt-3.5-turbo

Elige que endpoint de chat gpt utilizar. openai.api.url=https://api.openai.com/v1/chat/completions

API-Key que debemos solicitar a OpenAI. openai.api.key=

API-Key que debemos solicitar a Slack. slack.token=

ID del canal de Slack. slack.channel=
