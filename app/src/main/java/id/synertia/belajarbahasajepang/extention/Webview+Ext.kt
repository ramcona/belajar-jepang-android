package id.synertia.belajarbahasajepang.extention

import android.webkit.WebView

fun WebView.loadResponsiveHtml(htmlContent: String) {
    val responsiveHtml = """
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
            <style>
                body { 
                    margin: 0; 
                    padding: 0; 
                    font-size: 12px; 
                    word-wrap: break-word; /* Text wrapping */                    
                    overflow-wrap: break-word; /* Ensure word breaking */
                    white-space: normal; /* Allow wrapping */
                    max-width: 100%; /* Prevent horizontal overflow */
                }
                img { 
                    max-width: 100%; 
                    height: auto; /* Ensure images scale */
                }
                table { 
                    width: 100%; 
                    border-collapse: collapse; /* Ensure tables fit */
                }
            </style>
        </head>
        <body>$htmlContent</body>
        </html>
    """.trimIndent()

    // Enable zoom controls
    val webSettings = this.settings
    webSettings.setSupportZoom(true) // Enable zooming
    webSettings.builtInZoomControls = true // Built-in zoom controls
    webSettings.displayZoomControls = false // Hide zoom controls (optional)
    webSettings.useWideViewPort = true // Make the WebView scalable
    webSettings.loadWithOverviewMode = true // Ensure the page fits on screen

    this.loadDataWithBaseURL(null, responsiveHtml, "text/html", "UTF-8", null)
}
