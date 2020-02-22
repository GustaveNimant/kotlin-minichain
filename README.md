# Minichain : A blockchain in kotlin using a fork from (https://github.com/ligi/ipfs-api-kotlin) 
# Linux 
## sdkman (in order to install all the needed packages)
### installation of sdkman
    sudo apt install unzip
    sudo apt install zip
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
    (see also: https://sdkman.io/install)
   
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
*  sdk install java 19.3.0.2.r8-grl (_to be coherent with jacoco_)
*  sdk use java 19.3.0.2.r8-grl 

## kotlin
### installation of kotlin

*  sdk install kotlin
  
## ipfs-api-kotlin (optional)

## kotlin-minichain

   git clone https://github.com/GustaveNimant/kotlin-minichain.git

## code structure

|-- kotlin-minichain
|--- src
      | main
        | kotlin
          | io
            | ipfs
              | kotlin
      | test
        | kotlin
          | io
            | ipfs
              | kotlin

### installation of kotlin-minichain

## Compiling
   cd ~/sources/kotlin-minichain
   
   gradlew build 

## Running tests
   cd ~/sources/kotlin-minichain
   
   gradlew test 

## License 

MIT
