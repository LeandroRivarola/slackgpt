# slackgpt
Proyecto que envia los mensajes de un canal de slack a chat gpt para que los resuma utilizando las respectivas APIS.

El endpoint al que hay que consultar es un GET "http://{dominio:ip}/slack/summarize"

Para utilizarlo se deben establecer las variables de entorno en application.properties:

Establece que modelo de chat gpt utilizar.

openai.model=gpt-3.5-turbo


Elige que endpoint de chat gpt utilizar. 

openai.api.url=https://api.openai.com/v1/chat/completions


API-Key que debemos solicitar a OpenAI.

https://platform.openai.com/account/api-keys

openai.api.key=


API-Key que debemos solicitar a Slack, hay que crear una App e integrarla a nuestro grupo de Slack.

https://api.slack.com/apps 

slack.token=


ID del canal de Slack.
slack.channel=
