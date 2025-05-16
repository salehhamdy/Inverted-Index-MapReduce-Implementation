# Inverted Index – MapReduce Implementation

## Overview
This project implements an **Inverted Index** using the Hadoop MapReduce programming model in Java.  
An inverted index maps each unique word to the list of documents that contain it, forming the backbone of full-text search engines and many big‑data analytics pipelines.

## Repository / ZIP Contents
| Path | Description |
|------|-------------|
| `InvertedIndexDriver.java` | MapReduce **driver**: configures, packages, and submits the job. |
| `InvertedIndexMapper.java` | **Mapper**: emits `(word, documentId)` pairs. |
| `InvertedIndexReducer.java` | **Reducer**: aggregates document IDs for each word. |
| `part-r-00000` (or `output.txt`) | Sample **job output** generated on Hadoop. |
| `screenshot_yarn.png` | ResourceManager UI showing the completed job. |
| `screenshot_hdfs.png` | Terminal view of HDFS `input` and `output` directories. |
| *(optional extras)* | Any PDF/video references you wish to keep. |

> **Note:** All auxiliary IDE folders (`.idea`, `out/`, etc.) have been excluded from the submission ZIP for clarity.

## Prerequisites
* **JDK 8+**  
* **Hadoop 3.x** cluster with YARN and HDFS configured  
* `$HADOOP_HOME` added to your `PATH`

## Compiling the Code
1. **From the project root**, run:
   ```bash
   mkdir -p build
   javac -classpath "$(hadoop classpath)" -d build        InvertedIndexDriver.java InvertedIndexMapper.java InvertedIndexReducer.java
   ```
2. **Create a runnable JAR**:
   ```bash
   jar -cvf inverted-index.jar -C build/ .
   ```

## Running the Job
```bash
# 1. Put (or verify) your input directory on HDFS
hdfs dfs -mkdir -p /user/$USER/inverted_index/input
hdfs dfs -put local_docs/* /user/$USER/inverted_index/input

# 2. Execute the MapReduce job
hadoop jar inverted-index.jar InvertedIndexDriver       /user/$USER/inverted_index/input       /user/$USER/inverted_index/output

# 3. View results
hdfs dfs -cat /user/$USER/inverted_index/output/part-r-00000 | head
```

## Expected Output Format
```
<word>    <doc1>, <doc2>, <doc3>, ...
```
Example:
```
hadoop    doc1.txt, doc5.txt
index     doc2.txt
mapreduce doc1.txt, doc3.txt, doc4.txt
```

## Screenshots
Be sure to include in your report/ZIP:
* **ResourceManager UI** (`screenshot_yarn.png`) – shows Job ID, “SUCCEEDED”, map/reduce counts, etc.
* **HDFS listing** (`screenshot_hdfs.png`) – output of  
  ```bash
  hdfs dfs -ls /user/$USER/inverted_index/{input,output}
  ```

## Cleaning Up
```bash
hdfs dfs -rm -r -skipTrash /user/$USER/inverted_index/output
```
