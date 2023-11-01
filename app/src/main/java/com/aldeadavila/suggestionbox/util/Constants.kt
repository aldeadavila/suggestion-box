package com.aldeadavila.suggestionbox.util

object Constants {
    object Firestore {
        const val USERS = "users"
    }

    const val EMPTY_STRING = ""

    //Menu Items
    const val SIGN_OUT_ITEM = "Salir de la sesión"
    const val REVOKE_ACCESS_ITEM = "Revocar el acceso"

    //Labels
    const val EMAIL_LABEL = "Email"
    const val PASSWORD_LABEL = "Password"

    //Buttons
    const val SIGN_IN_BUTTON = "Iniciar sesión"
    const val RESET_PASSWORD_BUTTON = "Resetea la contraseña"
    const val SIGN_UP_BUTTON = "Cree su cuenta"

    //Messages
    const val RESET_PASSWORD_MESSAGE = "Le hemos enviado un email con un enlace para resetear la contraseña."
    const val VERIFY_EMAIL_MESSAGE = "Le hemos enviado un email con un link para verificar su cuenta de correo electrónico."
    const val REVOKE_ACCESS_MESSAGE = "Necistas iniciar sesión de nuevo antes de revocar el acceso."
    const val ACCESS_REVOKED_MESSAGE = "Su acceso ha sido revocado."
    const val WELCOME_MESSAGE = "Bienvenido al buzón de sugerencias de Aldeadávila."

    //Text
    const val FORGOT_PASSWORD = "¿Olvidó su contraseña?"
    const val NO_ACCOUNT = "Cree su cuenta nueva"
    const val LOGIN_GOOGLE = "O inicia sesión con"

    //Error Messages
    const val SENSITIVE_OPERATION_MESSAGE = "Esta operación es sensible y requiere de una reciente autenticación. Inicia sesión de nuevo antes de reintentar esta petición"

    const val USER_COLLECTION = "user_collection"
    const val SERVER_CLIENT_ID = "1:908793595011:android:6bb01f407c03af288887b6"

}