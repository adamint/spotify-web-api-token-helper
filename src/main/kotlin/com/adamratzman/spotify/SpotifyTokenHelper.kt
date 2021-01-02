package com.adamratzman.spotify

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.awt.Toolkit
import java.awt.datatransfer.ClipboardOwner
import java.awt.datatransfer.StringSelection

val clientId: String = System.getenv("SPOTIFY_CLIENT_ID")
val clientSecret: String = System.getenv("SPOTIFY_CLIENT_SECRET")
val redirectUri: String = System.getenv("SPOTIFY_REDIRECT_URI")

suspend fun main() {
    val authorizationUrl =
        getSpotifyAuthorizationUrl(clientId = clientId, redirectUri = redirectUri, scopes = SpotifyScope.values())
    println("Please go to this URL (doesn't have to be your own web server) to authorize your application: $authorizationUrl")
    println("------")
    println("Copy the entire URL that you're redirected to and paste it here...")

    val authorizationCode = readLine()!!.trim().split("apple.com/?code=").last()

    println("Building api...")
    val api = spotifyClientApi(
        clientId,
        clientSecret,
        redirectUri,
        SpotifyUserAuthorization(authorizationCode = authorizationCode)
    ).build()

    val token = api.token
    val accessToken = token.accessToken

    println("Copying access token to clipboard... Access token: $accessToken")
    writeToClipboard(accessToken, null)

    println("Printing token JSON...")
    println(Json.encodeToString(token))

    println("---")
    println("Done.")
}

fun writeToClipboard(s: String, owner: ClipboardOwner?) {
    val clipboard = Toolkit.getDefaultToolkit().getSystemClipboard()
    val transferable = StringSelection(s)
    clipboard.setContents(transferable, owner)
}
