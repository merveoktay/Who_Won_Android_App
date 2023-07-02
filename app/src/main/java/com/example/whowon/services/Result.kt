package com.example.whowon.services

import android.util.Log
import com.example.whowon.model.Raffle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document



class Result {
    fun raffle(url:String,type: String): List<Raffle> {
            val baseUrl="https://www.kimkazandi.com"

            val arr = mutableListOf<Raffle>()

            val doc: Document = Jsoup.connect(url).timeout(15000).get()
            val elementsClass = doc.select("div.row > div.item")

            for (item in elementsClass) {
                val img = item.getElementsByTag("img")
                val href =item.getElementsByTag("a").attr("abs:href")
                val src = baseUrl+img.attr("src")
                val title = item.select("h4").text()
                val date: String = item.select("div.title > span.date:nth-child(1)").text()
                val gift: String = item.select("div.title > span.date:nth-child(2)").text()
                val price: String = item.select("div.title > span.kosul_fiyat").text()

                if (title != "" && href != "" && date!="") {
                    val news = Raffle(null,title, src, href, date,gift,price,null, false,type)
                    arr.add(news)
                }
            }
            return arr
    }

    suspend fun fetchRaffleDetail(url: String): HashMap<String, String> = withContext(Dispatchers.IO) {
        val raffleDetailArr = HashMap<String, String>()

        try {
            val doc: Document = Jsoup.connect(url).timeout(15000).get()

            val startingDate = doc.select("div.brandDesc div.kalanSure:eq(0) h4").text()
            val endDate = doc.select("div.brandDesc div.kalanSure:eq(1) h4").text()
            val raffleDate = doc.select("div.brandDesc div.kalanSure:eq(2) h4").text()
            val listingDate = doc.select("div.brandDesc div.kalanSure:eq(3) h4").text()
            val minSpendAmount = doc.select("div.brandDesc div.kalanSure:eq(4) h4").text()
            val totalGiftValue = doc.select("div.brandDesc div.kalanSure:eq(5) h4").text()
            val totalNumberOfGifts = doc.select("div.brandDesc div.kalanSure:eq(6) h4").text()

            val raffleDetail = doc.select("div.secondGallery div#home")

            val raffleInfoTitle = raffleDetail.select("div.scrollbar-dynamic:eq(0) h3").text()
            val raffleInfoContent = raffleDetail.select("div.scrollbar-dynamic:eq(0) p").text()

            val raffleDateTitle = raffleDetail.select("div.scrollbar-dynamic:eq(1) h3").text()
            val raffleDateContent = raffleDetail.select("div.scrollbar-dynamic:eq(1) p").text()

            val raffleResultsTitle = raffleDetail.select("div.scrollbar-dynamic:eq(2) h3").text()
            val raffleResultsContent = raffleDetail.select("div.scrollbar-dynamic:eq(2) p").text()

            val giftsTitle = raffleDetail.select("div.scrollbar-dynamic:eq(2) h3:contains(Hediyeler:)").text()
            val giftsContent = raffleDetail.select("div.scrollbar-dynamic:eq(2) p:contains(Hediyeler:) + p").text()


            val detail = raffleInfoTitle+raffleInfoContent+raffleDateTitle+raffleDateContent+
                    raffleResultsTitle+raffleResultsContent+giftsTitle+giftsContent
            raffleDetailArr["startingDate"] = startingDate
            raffleDetailArr["endDate"] = endDate
            raffleDetailArr["raffleDate"] = raffleDate
            raffleDetailArr["minSpendAmount"] = minSpendAmount
            raffleDetailArr["totalGiftValue"] = totalGiftValue
            raffleDetailArr["totalNumberOfGifts"] = totalNumberOfGifts
            raffleDetailArr["listingDate"] = listingDate
            raffleDetailArr["raffleDetail"] = detail
        } catch (e: Exception) {
           Log.e("Error",e.toString())
        }

        raffleDetailArr
    }
}


