call mvn eclipse:clean
call mvn eclipse:eclipse -DdownloadSources=true
call mvn dependency:build-classpath -U -Pdeploy -Dmdep.pathSeparator=;  -DexcludeScope=provided -Dmdep.outputFile=jarList.txt
@pause
