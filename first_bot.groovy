node('master') {
withCredentials([string(credentialsId: 'bb900ccd-0e5e-43ed-b79b-24defe484f37', variable: 'bot_code')]) {
  sh label: '', script: """curl -v \\
    -H "Authorization: "$bot_code"" \\
    -H "User-Agent: Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36" \\
    -H "Content-Type: application/json" \\
    -X POST \\
    -d \'{"content":"$discord_message"}\' \\
    https://discord.com/api/webhooks/793885194679484448/JyLtuIabfC2ngnoKX2T8DU8lMU2v46fKSl2irjJXrqu9Np-HL3hACAXsBT1f6g6ws-00"""
}
}

