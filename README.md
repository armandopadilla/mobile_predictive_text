Mobile Predictive Text 
==========================

Action Plan
==========================
install db (Done)
run with db (Done)
test a few sentences (Doing) - 
Found some issues with the way we parse the text. I wasn't capturing the previous word.  (Fixed)
Noise such as ?,.! will stop searching (Fixed)
Install 2-gram data
Major issues here with the size of the files.  Used the unix command "split" to break the files up into 1,000,000 lines per file.  This also crashed Google Refine. 
For sake of time I kept 1-gram for now.
Algorithm Look up
T9 (Text on 9 keys)
Used by translating NUMBERS to words. 
4663 = home or good for example.
Has collection of digits + words in its dictionary table.
Looks at neighboring keys just in case you mistyped (Idea #2)
Looks at frequency.
How common is this word in YOUR vocabulary. (Idea #3)
Leventein
Introduced for misspelled word.  What if the user types something in but its not found because its just spelled wrong
Reason why it didn't work:  
We have to take the distance for ALL words when comparing it with your word.
Heavy computation power can be MANY words
Should compute this before hand.  (is this necessary??)
Frequency
Taken from T9 but applied to what I'm doing. 
As the user uses a specific word recommended I increase its "usage count"  
When we recommend a word we take into account this frequency AND Probability
Still working on it.
If I had more time.
Finish Frequency
Neural Net 
Need to capture if the prediction was a success (used or not)
Genetic Algorithm
Found a paper that did this.  Read it!!!


Clean up and look up Process
==========================
Done
Remove noise  - ?,.+*&^%$#@!
get full sentence
break up by space
get n-1 word 
get current text unto the curser
get everything after he curser up to the space.


