# Minichain : A blockchain in kotlin using a fork from (https://github.com/ligi/ipfs-api-kotlin) 

## sdkman
### installation of sdkman
 https://sdkman.io/install

 $ curl -s "https://get.sdkman.io" | bash

### usage of sdkman
  sdk list <package>
  sdk uninstall <package> x.y.z
  sdk install <package>
  sdk use gradle <package> x.y.z
  sdk flush archives

## gradle
### installation of gradle
  sdk list gradle
  sdk install gradle 6.1.1

### build.gradle.kts
(https://github.com/GustaveNimant/kotlin-minichain/blob/master/build.gradle.kts)

## java 
### installation of java
  sdk list java
  sdk use java 19.3.0.2.r8-grl (to be coherent with jacoco)

## kotlin
### installation of kotlin

  sdk install kotlin
  
## ipfs-api-kotlin
 (https://github.com/ligi/ipfs-api-kotlin) [ ligi / ipfs-api-kotlin ]
  git clone https://github.com/ligi/ipfs-api-kotlin.git

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
