package entities

import com.spotify.scio.extra.csv.CsvIO
import kantan.csv.RowDecoder

object part2InstallEntities  {

  class nbaPlayerExample {
    // case class representing a Row of our csv
    protected case class nbaPlayer(
                                    index: Int,
                                    player: String,
                                    height: Option[Int],
                                    weight: Option[Int],
                                    collage: Option[String],
                                    born: Option[Int],
                                    birthCity: Option[String],
                                    birthState: Option[String]
                                  )

    // A decoder saying to scala how to parse each CSV Row Element to nbaPlayer case class
    protected implicit val decoderNbaDraft: RowDecoder[nbaPlayer] = RowDecoder.ordered {
      (
        index: Int,
        player: String,
        height: Option[Int],
        weight: Option[Int],
        collage: Option[String],
        born: Option[Int],
        birthCity: Option[String],
        birthState: Option[String]
      ) =>
        nbaPlayer(index,player,height,weight,collage,born,birthCity,birthState)
    }
    // csv Read and Write Config saying how to read csv without Headers and how to write with to columns with headers "state" and "biggest_height"
    protected lazy val csvReadConfig: CsvIO.ReadParam = CsvIO.ReadParam(csvConfiguration = CsvIO.DefaultCsvConfig.withoutHeader)
    protected lazy val csvWriteConfig: CsvIO.WriteParam = CsvIO.WriteParam(csvConfiguration = CsvIO.DefaultCsvConfig.withHeader("state","biggest_height"))

  }

}
