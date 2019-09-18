
Run the Spring Boot application and navigate to:

http://localhost:8080/estimate?keyword=iphone 

Returns {"keyword":"iphone","score":50}

Assumptions:
-
Amazon probably shows keyword suggestions that are trending at the moment,
but it could also depend on location and previous searches of the user.
Probably Amazon places the products that sells more in the suggestions.
Also, Amazon probably suggests products that other customers have searched
for and bought.

The url that I use for getting the keywords returns 10 keywords every time.
Other assumptions that you have provided in the PDF file hold true.
The URL: https://completion.amazon.com/api/2017/suggestions

Algorithm:
-
So my strategy goes like this:

get the suggestions for each keyword's substrings and try to come up with
an estimation. For example, if keyword is iphone:
Get the results for 'i'
Then, Get the results for 'ip'
Then, Get the results for 'iph'
Then, Get the results for 'ipho'

And so on...
