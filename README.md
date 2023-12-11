# Word Counter

This is an assignment as part of an interview process

## About

Word Counter is a library and an RPC api that enables the user to count the frequency
of certain words in a text.

### Endpoints

The RPC api has 3 endpoints

- calculateFrequencyForWord, to count the frequency of a certain word
- calculateMostFrequentNWords, to get a list of the most frequent words
- calculateHighestFrequency, to get the frequency of the most frequent word

Instructions on how to use these endpoints can be found in the openapi spec at **word-counter-web/src/main/resources/openapi**

### Architecture

The project consists of 2 maven modules

- word-counter-lib module, which does not have a dependency on spring
- word-counter-web module, which is dependent on the lib module and spring
