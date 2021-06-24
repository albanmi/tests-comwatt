/*
FROM: https://github.com/lanastazia/homework/tree/master/backend/scala/exercices/2-radar

Automated Speed Processing System

Description of the program

You need to detect speeding vehicles by calculating their average speeds between two snapshots taken on a road. Your program must determine which vehicles are in violation and return a list of tickets containing the vehicle registration, the mileage at which the violation was detected, and the calculated average speed (rounded up).

The input data contains all time-stamped records for each license plate detected. All records are grouped by license plate and the camera distances are sorted in ascending order within this group.

Resolution

Create a program (executable manually or via an automated test set) in src/main/scala.









Test sets as text files are in: /scala/commandLine/speeding-radar/src/main/resources

A. Mirre

*/

import scala.io.Source
import scala.collection.mutable.ListBuffer
import org.scalatest.Assertions._


object ReadFile{

	// Read a .txt file from src/main/resources/ folder.
	def textFormat(filename : String) : Seq[String] = {
		val bufferedSource = Source.fromResource(filename)
		val lines = (for (line <- bufferedSource.getLines()) yield line).toList

    	bufferedSource.close

		lines
	}
}

object SpeedingRadar{

	// Extract the maximum allowed speed for the input format given in the Subject.
	def maxSpeed(data : Seq[String]) : Int = {
		val maxSpeed = data(0).split(" ")(3).toInt

		maxSpeed
	}

	// Compute the speed as a delta of position over a delta of time. 
	def computeSpeed(previousRecord: String, currentRecord: String) : Double = {
		val currentMilepost = currentRecord.split(",")(1).toDouble
		val currentTimestamp = currentRecord.split(",")(2).toDouble

		val previousMilepost = previousRecord.split(",")(1).toDouble
		val previousTimestamp = previousRecord.split(",")(2).toDouble

		val speed = math.ceil(((currentMilepost - previousMilepost) / ((currentTimestamp - previousTimestamp)/3600))).ceil

		speed
	}

	// Check if two number plates are the same.
	def sameNumberPlate(previousRecord: String, currentRecord: String) : Boolean = {
		val currentNumberPlate = currentRecord.split(",")(0)
		val previousNumberPlate = previousRecord.split(",")(0)

		(currentNumberPlate == previousNumberPlate)
	}

	// Determine which car is speeding.
	def isSpeeding(data : Seq[String]) : ListBuffer[String]={
		val output =  new ListBuffer[String]()

		val rangeOfRecords = 2 to data.length-1
		
		for(record <- rangeOfRecords){
			val previousRecord = data(record-1)
			val currentRecord = data(record)

			if(sameNumberPlate(previousRecord, currentRecord)){
				val speed = computeSpeed(previousRecord, currentRecord)

				if(speed > maxSpeed(data)){
					val numberPlate = currentRecord.split(",")(0)
					val milepost = currentRecord.split(",")(1)

					val speedingCar = numberPlate+","+milepost+","+String.format("%.0f",speed)

					output += speedingCar
				}
			}
		}

		output.foreach(outputItem => println(outputItem))

		output
	}
}

object Main extends App{

	val filename1 = "input1.txt"
	val data1 = ReadFile.textFormat(filename1)
	val output1 = SpeedingRadar.isSpeeding(data1)
	
	assertResult("ZBZJ42,275,101"){output1(0)}

	val filename2 = "input2.txt"
	val data2 = ReadFile.textFormat(filename2)
	val output2 = SpeedingRadar.isSpeeding(data2)
	
	assertResult(ListBuffer()){output2}

	val filename3 = "input3.txt"
	val data3 = ReadFile.textFormat(filename3)
	val output3 = SpeedingRadar.isSpeeding(data3)
	
	assertResult("PAZD54,150,100"){output3(0)}
	assertResult("PAZD54,250,102"){output3(1)}
	assertResult("DJSS87,250,100"){output3(2)}
	
}
