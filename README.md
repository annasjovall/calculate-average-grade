# calculate-average-grade
Räkna ut dina betyg från lunds universitet automatiskt med ett ladok utdrag.


A way for Lund University students to calculate their grade average automatically with a ladok excerpt.


## Method
- First, download the excerpt from [ladok](student.ladok.se/student). Under 'Intyg' select 'Hämta intyg' and choose 'Resultatintyg' in English. Save the pdf file at an appropriate location.
- Second, download the jar file 'CalculateAvg.jar' from this repository.
- Finally, run the jar file in the terminal. The jar file takes the file name of the excerpt as input.


If the excerpt is called 'Intyg.pdf' and located in the same directory as the jar file you can simply input `java -jar CalculateAvg.jar "Intyg.pdf"`.
