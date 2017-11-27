import java.io.{BufferedWriter, File, FileWriter}
import java.time.LocalDateTime

object LogGenerator {

  // Define strings in Scala: https://alvinalexander.com/scala/scala-string-array-examples-how-create-arrays
  val urls = Array("urlA", "urlB", "urlC")
  val ips = Array("ipA", "ipB", "ipC")
  val browsers = Array("Chrome", "Safari", "Internet_Explorer", "Firefox")
  val clicksPerSession = 5
  val logDelimiter = '\t'

  /*
  *
  */
  def main(args: Array[String]): Unit = {
    println("Start Main function")

    val z:Array[String] = Array("Log1", "Log2", "Log3")

    val records = generateLogs(LocalDateTime.now(), LocalDateTime.now().plusDays(1))

    writeToNewLogFile(records)

    //writeToLogFile(z)
  }

  def generateLogs (startTime: LocalDateTime, endTime: LocalDateTime): Array[String] = {

    // Logic - Start with the 'start' time, and create records at 'rand()*5 seconds' intervals.
    // Increment the time by 5 seconds while looping through and creating records

    var records = new Array[String](0)
    var counterTime: LocalDateTime = startTime

    while (counterTime.isBefore(endTime)) {
      val addSeconds: Long = (scala.math.random()*5).toLong
      val recordTime = counterTime.plusSeconds(addSeconds)
      // Create a record with random url and ip
      //println(urls(0))
      val record: String = {
        createLogRecord(this.urls((scala.math.random() * this.urls.length).toInt), this.ips((scala.math.random() * this.ips.length).toInt), recordTime, this.browsers((scala.math.random() * this.browsers.length).toInt))
      }
      println(record)
      //  LogRecord(self.generateRandomURL(), self.generateRandomIP(), recordTime.strftime('%Y:%m:%d:%M:%S'), self.generateRandomBrowser())
      records = records :+ record
      counterTime = counterTime.plusSeconds(5)
    }

    return records

  }

  def createLogRecord (
                      url: String,
                      ip: String,
                      time: LocalDateTime,
                      browser: String
                      ): String =
  {
    return url + this.logDelimiter + ip + this.logDelimiter + time.toString + this.logDelimiter + browser
  }

  /*
  * https://alvinalexander.com/scala/how-to-write-text-files-in-scala-printwriter-filewriter
  *
  */
  def writeToNewLogFile (logItems: Array[String]): Unit = {
    // Create file
    val file = new File("generatedLog.log")

    // Set up file writer
    val bw = new BufferedWriter(new FileWriter(file))

    // Append content, one array per line
    for (item <- logItems) {
      bw.write(item)
      bw.write('\n')
    }

    bw.close()
  }

  def writeToFile (content: Array[String]): Unit = {
    // Open file. If does not exist, create file.

    // Set up file writer

    // Append content, one array per line
  }
}
