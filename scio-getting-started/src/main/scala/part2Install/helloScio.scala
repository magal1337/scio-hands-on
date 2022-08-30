package part2Install
import com.spotify.scio._
import com.spotify.scio.extra.csv.{CsvScioContext, WritableCsvSCollection}
import com.spotify.scio.values.SCollection
import entities.part2InstallEntities.nbaPlayerExample




object helloScio extends nbaPlayerExample with Serializable {

  def main(cmdlineArgs: Array[String]): Unit = {

    val (sc, args) = ContextAndArgs(cmdlineArgs)

    val nbaDf: SCollection[nbaPlayer] = sc.csvFile(args("input"), csvReadConfig)

    val tallestPlayers: SCollection[(String,Int)] = nbaDf
      .keyBy(_.birthState)
      .map(x => (x._1,x._2.height))
      .maxByKey
      .filter(_._1.getOrElse("null") != "null")
      .map(x=> (x._1.get,x._2.getOrElse(0)))
      .filter(_._2  >= 200)

    tallestPlayers.map(println)

    tallestPlayers.saveAsCsvFile(args("output"),csvWriteConfig)

    sc.run()
  }

}
