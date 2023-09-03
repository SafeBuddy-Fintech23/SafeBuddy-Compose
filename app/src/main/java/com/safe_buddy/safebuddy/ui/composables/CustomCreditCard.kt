package com.safe_buddy.safebuddy.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safe_buddy.safebuddy.R
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CustomCreditCard(
    id: Long = 3646592653586346,
    balance: Int = 5000,
    userName: String = "username username"
) {
    Card(
        modifier = Modifier
            .width(400.dp)
            .height(240.dp)
            .padding(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_card_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(Modifier.padding(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_round),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "Credit Card",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace
                    )
                }
                Text(
                    text = formatLongToSpacedString(id),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Balance",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = formatIntToDollarCurrency(amount = balance),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = userName,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "SafeBuddy",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomCreditCardPreview() {
    CustomCreditCard()
}

/**
 * Formats a Long number into a spaced string with groups of four digits from right to left.
 *
 * @param input The Long number to be formatted.
 * @return A String representing the formatted number.
 */
fun formatLongToSpacedString(input: Long): String {
    // Convert the input Long to a String
    val inputStr = input.toString()

    // Reverse the string to start grouping digits from right to left
    val reversedStr = inputStr.reversed()

    // a StringBuilder to build the formatted string
    val formattedStr = StringBuilder()

    // Iterate through each character in the reversed string
    for (i in reversedStr.indices) {
        // Add a space every four digits (groups of four)
        if (i > 0 && i % 4 == 0) {
            formattedStr.append(' ')
        }

        // Append the current character to the formatted string
        formattedStr.append(reversedStr[i])
    }

    // Reverse the formatted string to the correct order
    return formattedStr.reverse().toString()
}

/**
 * Formats an Integer as a currency in dollars with .00 at the end.
 *
 * @param amount The Integer amount in dollars to be formatted.
 * @return A String representing the formatted currency in dollars with .00 at the end.
 */
fun formatIntToDollarCurrency(amount: Int): String {
    // Create a Locale for the United States (US) to format as dollars
    val usLocale = Locale("en", "US")

    // Create a NumberFormat instance for currency formatting in dollars
    val currencyFormat = NumberFormat.getCurrencyInstance(usLocale)

    // Set the minimumFractionDigits to 2 to ensure .00 at the end
    currencyFormat.minimumFractionDigits = 2

    // Format the Integer amount as currency
    return currencyFormat.format(amount.toDouble())
}