# Minichain : A blockchain in kotlin using a fork from (https://github.com/ligi/ipfs-api-kotlin) 

## sdkman (in order to install all the needed packages)
### installation of sdkman
    https://sdkman.io/install
    $ curl -s "https://get.sdkman.io" | bash

### usage of sdkman
*  sdk list _package_
*  sdk uninstall _package_ x.y.z
*  sdk install _package_
*  sdk use gradle _package_ x.y.z
*  sdk flush archives

## gradle
### installation of gradle
*  sdk list gradle
*  sdk install gradle 6.1.1

### gradle init 

### build.gradle.kts
(https://github.com/GustaveNimant/kotlin-minichain/blob/master/build.gradle.kts)

### running gradle

#### settings.gradle.kts
     
     rootProject.name = 'kotlin-minichain'
     rootProject.buildFileName = "build.gradle.kts"

     gradlew build
     gradlew -q test
     gradlew test --info
     gradlew run --args="-debug all"
    
## java 
### installation of java
*  sdk list java
*  sdk use java 19.3.0.2.r8-grl (_to be coherent with jacoco_)

## kotlin
### installation of kotlin

*  sdk install kotlin
  
## ipfs-api-kotlin (optional)

## kotlin-minichain

   git clone https://github.com/GustaveNimant/kotlin-minichain.git
  
### installation of kotlin-minichain

## Compiling
   cd ~/sources/kotlin-minichain
   
   gradlew build 

## Running tests
   cd ~/sources/kotlin-minichain
   
   gradlew test 

## License 

MIT
