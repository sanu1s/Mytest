# Mytest

1. Problem Statement - Finds out the total Inventory Availability for the respective product and date based upon the user input and the data set Up

End Point
/getInvPicture

Sample Input
```
{ 
"productId":"Prod1",
"prodName":"shirt", 
"reqDate": "2021-02-18"
	
}
```

Sample Output
```
{
    "productId": "Prod1",
    "prodName": "shirt",
    "availQty": "50"
}
```

Validation: If the input date is more than 30 days of sysdate it will fail

Data Set Up
```Shirt-10 -2021-03-19
Shirt-20 -2021-03-21
Shirt-10 -2021-03-29
```
Logic: It checks out the sum of inventory availability based upon the user Input Date +10 days.

2. Problem Statement - Finds out the Store Availability based upon the inventory avialability and Capacpity Picture

End Point
/getProdAvailability

Sample Input
```
{
  "storeNo": "Store001",
  "productId": "Prod1",
  "reqQty": "1",
  "reqDate": "2021-02-20"
}
```

Sample Output
```
{
    "storeNo": "Store001",
    "productId": "Prod1",
    "reqQty": "1",
    "reqDate": "2021-02-20",
    "status": "Available"
}
```

```
Data Set Up

Availability Picture
Prod1-Store001 -2021-02-19,1
Prod1-Store001 -2021-02-20,3
Prod2-Store001 -2021-02-21,0

Capacity Picture
Prod1-Store001-2021-02-19,0
Prod1-Store001-2021-02-20,2
Prod1-Store001-2021-02-21,2
Prod1-Store001-2021-02-22,0

```
