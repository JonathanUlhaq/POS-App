package com.example.posapp.utils
//
//import android.content.Context
//import android.util.DisplayMetrics
//import com.example.posapp.R
//import com.khairo.escposprinter.EscPosPrinter
//import com.khairo.escposprinter.connection.bluetooth.BluetoothPrintersConnections
//import com.khairo.escposprinter.textparser.PrinterTextParserImg
//
//
//class BluetoothPrint (
//    val context:Context
//        ) {
//    fun print() {
//        val printer = EscPosPrinter(
//            BluetoothPrintersConnections.selectFirstPaired(),
//            203,
//            48f,
//            32
//        )
//        val formattedText = formatText(printer)
//        printer.printFormattedText(formattedText)
//
//    }
//
//    private fun formatText(printer:EscPosPrinter):String {
//        val satu = 1
//        return "[C]<img>"+ PrinterTextParserImg.bitmapToHexadecimalString(
//            printer,
//            context.resources.getDrawableForDensity(
//                R.drawable.ic_launcher_foreground,DisplayMetrics.DENSITY_MEDIUM
//            )
//        ) + "</img>\n" +
//                "[L]\n"+
//                "[C]<b> Print $satu ni ? </b>" +
//                "[L]Iya nih"
//    }
//}