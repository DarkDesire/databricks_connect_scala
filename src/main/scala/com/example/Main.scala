import com.databricks.connect.DatabricksSession
import com.databricks.sdk.core.DatabricksConfig
import org.apache.spark.sql.functions.{call_udf, col}
import com.example.udf.{MyCustomArrayUDFs, SparkUDFUtil};
import java.io.File

object Main extends App {
  // Connect to Databricks Runtime 14.3 LTS
  val config = new DatabricksConfig()
    .setHost("DATABRICKS_HOST")
    .setToken("DATABRICKS_TOKEN")
    .setClusterId("DATABRICKS_CLUSTER_ID") 

  val spark = DatabricksSession
    .builder()
    .sdkConfig(config)
    .getOrCreate()

  spark.addArtifact(SparkUDFUtil.getSparkUDFModuleJarFilePath)

  import spark.implicits._

  val values = Seq(
    (Seq(1.0, Double.NaN, Double.NaN, 2.0, Double.NaN, 3.0)),
    (Seq(3.0, Double.NaN, 4.0, 5.0, Double.NaN, 6.0))
  )
  val df = values.toDF("values")
  df.show(2, false)

  MyCustomArrayUDFs.registerAllUDFs(spark)
  // throws error:
  // Exception in thread "main" org.apache.spark.SparkException: 
  // java.io.InvalidClassException: org.apache.spark.sql.types.ArrayType; 
  // local class incompatible: stream classdesc serialVersionUID = -8361568169218636135, 
  // local class serialVersionUID = 8145303878994261768

  // val lastDF = df.withColumn(
  //   MyCustomArrayUDFs.REMOVE_CONSECUTIVE_NANS,
  //   call_udf(MyCustomArrayUDFs.REMOVE_CONSECUTIVE_NANS, col("values"))
  // )
  // lastDF.show(2, false)
}
