Mobile Predictive Text 
==========================
Overview 
--------
What started off as a class project is now a hobby.  This is my first version of a Android based mobile predictive text application. 
The current implementation uses Gussian Probability to determine the Probability of each word in the world.  The world is installed 
within a database of ~40k words.  

Dataset
-------
* Bag of Words: http://archive.ics.uci.edu/ml/datasets/Bag+of+Words
* Using the New York Times word list.


Action Plan
==========================
1. Install DB
2. run with db (Done)
3. test a few sentences (Doing) - Found some issues with the way we parse the text. I wasn't capturing the previous word.  (Fixed)
Noise such as ?,.! will stop searching (Fixed)
4. Install 2-gram data - Major issues here with the size of the files.  Used the unix command "split" to break the files up into 1,000,000 lines per file.  This also crashed Google Refine. For sake of time I kept 1-gram for now.


Algorithm Look up
==========================
* T9 (Text on 9 keys)
Used by translating NUMBERS to words. 
4663 = home or good for example.
Has collection of digits + words in its dictionary table.
Looks at neighboring keys just in case you mistyped (Idea #2)
Looks at frequency.
How common is this word in YOUR vocabulary. (Idea #3)

* Leventein
Introduced for misspelled word.  What if the user types something in but its not found because its just spelled wrong
Reason why it didn't work:  
We have to take the distance for ALL words when comparing it with your word.
Heavy computation power can be MANY words
Should compute this before hand.  (is this necessary??)

* Frequency
Taken from T9 but applied to what I'm doing. 
As the user uses a specific word recommended I increase its "usage count"  
When we recommend a word we take into account this frequency AND Probability
Still working on it.


Next Steps
===================
1. Finish Frequency
2. Compress Database.  
3. Compute Leventein for words.
4. Integrate Google' N-Gram files. (2-Gram only)

Clean up and look up Process
==========================
* Remove noise  - ?,.+*&^%$#@!
* get full sentence
* break up by space
* get n-1 word 
* get current text unto the curser
* get everything after he curser up to the space.


