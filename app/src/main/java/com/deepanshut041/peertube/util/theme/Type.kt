package com.deepanshut041.peertube.util.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.font.font
//import androidx.compose.ui.text.font.fontFamily
import com.deepanshut041.peertube.R
//
//private val fonts = fontFamily(
//    font(R.font.roboto_thin, FontWeight.Thin, FontStyle.Normal),
//    font(R.font.roboto_light, FontWeight.Light, FontStyle.Normal),
//    font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
//    font(R.font.roboto_medium, FontWeight.Medium, FontStyle.Normal),
//    font(R.font.roboto_bold, FontWeight.Bold, FontStyle.Normal),
//    font(R.font.roboto_black, FontWeight.Black, FontStyle.Normal),
//    font(R.font.roboto_thin_italic, FontWeight.Thin, FontStyle.Italic),
//    font(R.font.roboto_light_italic, FontWeight.Light, FontStyle.Italic),
//    font(R.font.roboto_regular_italic, FontWeight.Normal, FontStyle.Italic),
//    font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
//    font(R.font.roboto_bold_italic, FontWeight.Bold, FontStyle.Italic),
//    font(R.font.roboto_black_italic, FontWeight.Black, FontStyle.Italic)
//)

//val typography = typographyFromDefaults(
//    h1 = TextStyle(fontFamily = fonts),
//    h2 = TextStyle(fontFamily = fonts),
//    h3 = TextStyle(fontFamily = fonts),
//    h4 = TextStyle(fontFamily = fonts),
//    h5 = TextStyle(fontFamily = fonts),
//    h6 = TextStyle(fontFamily = fonts),
//    subtitle1 = TextStyle(fontFamily = fonts),
//    subtitle2 = TextStyle(fontFamily = fonts),
//    body1 = TextStyle(fontFamily = fonts),
//    body2 = TextStyle(fontFamily = fonts),
//    button = TextStyle(fontFamily = fonts),
//    caption = TextStyle(fontFamily = fonts),
//    overline = TextStyle(fontFamily = fonts)
//)

fun typographyFromDefaults(
    h1: TextStyle?,
    h2: TextStyle?,
    h3: TextStyle?,
    h4: TextStyle?,
    h5: TextStyle?,
    h6: TextStyle?,
    subtitle1: TextStyle?,
    subtitle2: TextStyle?,
    body1: TextStyle?,
    body2: TextStyle?,
    button: TextStyle?,
    caption: TextStyle?,
    overline: TextStyle?
): Typography {
    val defaults = Typography()
    return Typography(
        h1 = defaults.h1.merge(h1),
        h2 = defaults.h2.merge(h2),
        h3 = defaults.h3.merge(h3),
        h4 = defaults.h4.merge(h4),
        h5 = defaults.h5.merge(h5),
        h6 = defaults.h6.merge(h6),
        subtitle1 = defaults.subtitle1.merge(subtitle1),
        subtitle2 = defaults.subtitle2.merge(subtitle2),
        body1 = defaults.body1.merge(body1),
        body2 = defaults.body2.merge(body2),
        button = defaults.button.merge(button),
        caption = defaults.caption.merge(caption),
        overline = defaults.overline.merge(overline)
    )
}
