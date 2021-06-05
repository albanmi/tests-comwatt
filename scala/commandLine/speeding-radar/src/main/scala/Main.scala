import upickle.default._

/*
FROM: https://github.com/lanastazia/homework/tree/master/backend/scala/exercices/2-radar

Automated Speed Processing System

Description of the program

You need to detect speeding vehicles by calculating their average speeds between two snapshots taken on a road. Your program must determine which vehicles are in violation and return a list of tickets containing the vehicle registration, the mileage at which the violation was detected, and the calculated average speed (rounded up).

The input data contains all time-stamped records for each license plate detected. All records are grouped by license plate and the camera distances are sorted in ascending order within this group.

Resolution

Create a program (executable manually or via an automated test set) in src/main/scala.

Test sets as json files are in: /scala/commandLine/speeding-radar/src/data/








A. Mirre

*/





object Main extends App {

	val numberplate = 0
	val milepost = 1
	val timestamp = 2
	val maxspeed = 3

	
	for(k <- 1 to 3){
		val file = "input"+k.toString+".json"
		val jsonString = os.read(os.pwd/"src"/"data"/file)
		var data = ujson.read(jsonString)
		data = data("data")

		println("\nInput "+k)

		for(i <- 1 to data.arr.length-1){

			if(data.arr(i)(numberplate).value == data.arr(i-1)(numberplate).value){
				val speed = ((data.arr(i)(milepost).num - data.arr(i-1)(milepost).num) / ((data.arr(i)(timestamp).num - data.arr(i-1)(timestamp).num)/3600)).ceil

				if(speed > data.arr(i)(maxspeed).num){
					println(data.arr(i)(numberplate).str + "," + data.arr(i)(milepost).num + "," + speed)
			}}


			
		}
	}
}