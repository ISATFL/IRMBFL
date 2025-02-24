# IRMBFL

# Ⅰ. Request
- Java 1.8
- Defects4J(1.4.0)
- Git >= 1.9
- SVN >= 1.8
- Perl >= 5.0.10

# Ⅱ. Prepare Defects4J Datasets
1. Download and install [Defects4J 1.4.0](https://github.com/rjust/defects4j/tree/v1.4.0), after which the required settings described in its readme file must be completed.
2. Check out and compile each bug. There are 395 bugs in total. The detailed procedure is as follows:
    1. `defects4j checkout -p Lang -v 1b -w /tmp/lang_1_buggy`
    2. `cd /tmp/lang_1_buggy`
    3. `defects4j compile`
3. Export bug report id and bug report url for each bug.
    1. `defects4j query -p Lang -q "report.id,report.url"`
4. Since the official Defects4J only provides the bug report URL, but we need a bug report in XML format as input, we need to construct the required format. Here, the Lang-1 project is used as a reference example, stored at the path ./data/BugReport/Lang/Lang1.xml.

# Ⅲ. Get Suspicious File Ranking and Mutation Information
## (1). Get Suspicious File Ranking Information
1. Configure the BugLocator tool to output a list of suspicious files using bug reports and code files as inputs.
```BugLocator
https://code.google.com/archive/p/bugcenter/downloads
```
2. As an example, for the Lang-1 project, output the BugLocator results to the directory ./data/BugLocator/IRBL/Lang/IR_Lang1.txt.
3. Follow the above process to complete the bug analysis for 395 bugs in Defects4J(1.4.0).

## (2). Get Mutation Information
1. Configure the Killmap tool to collect mutation information of the suspicious files.
```
https://bitbucket.org/rjust/fault-localization-data
```
2. Follow the readme instructions to get the killmap.csv and mutants.log results for each bug. For Lang-1 as an example, output the results to the directory ./data/D4J(1.4)/Lang/1/killmaps/Lang/1.
3. If you wish to skip the Killmap tool and quickly obtain results, you can download them from the following link:
```
https://fault-localization.cs.washington.edu/data/
```

# Ⅳ. Get Results
Use killmap.csv and mutants.log from the mutation information as input of Main.java file and output the list of suspicious statements and evaluation results.


