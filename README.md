# rdf-converter-cmdline
Java-based command line software with the aim of converting rdf files in nquads format to json files following datacite schema
(https://github.com/datacite/schema/blob/master/source/json/kernel-4.3/datacite_4.3_schema.json).
It takes as input a folder containing nquads file generated by bmuse (https://github.com/HW-SWeL/BMUSE) 
and assumes that every .nq file is related to one single scraped url.

## Compilation requirements
- git client
- Java 1.8
- Maven 3.6.0 (or above)

## Compilation instructions

```
git clone https://github.com/openaire/rdf-converter-cmdline.git                                                                                                   
Cloning into 'rdf-converter-cmdline'...
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Compressing objects: 100% (5/5), done.
remote: Total 5 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (5/5), done.

cd rdf-converter-cmdline/
mvn package
```

The compiled binary will be available under the `target` project subdirectory.

In UNIX-like systems the client binary can be executed with

```
./rdf-converter-cmdline-2.3.4.RELEASE.jar 
```

In Windows systems the client binary can be executed with
```
java -jar ./rdf-converter-cmdline-2.3.4.RELEASE.jar 
```


## Software synopsis

``` 
===================================
Command line RDF converter
===================================

usage: ./rdf-converter-cmdline.jar [-h] [-i] <arg> -o <arg> [-j] [-v] [-vv]
        [-z]
-h,--help             help
-i,--input <arg>      the input directory (REQUIRED)
-o,--output <arg>     the output directory (REQUIRED)
-j,--jsonld          print input data with json-ld format
-v                    verbose
-vv                   show debug logs

```


## Example usage

```
./rdf-converter-cmdline-2.3.4.RELEASE.jar -i /tmp/rdf-converter-input -o /tmp/rdf-converter-output -j
```

The command above performs the following actions
- foreach file stored in nquads format in the given input path (-i) generates a json file following datacite schema in the given output path (-o)
- for each conversion a json-ld file will be printed on standard output corresponding to the input file data

