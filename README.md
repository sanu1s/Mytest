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
```
Shirt-10 -2021-03-19
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

Logic: Computes the inventory availability for the particaulr store based upon the capacity and availability picture of that particular store for the requested date


3. Problem Statement - Finds out the max/min Sold out product based upon the user Input

	End Point
	/getOrderStats

Sample Input
```
{
	"statName":"MAX_SALE"
}
```

Sample Output
```
{
    "productId": "Product1",
    "orderList": [
        {
            "orderNo": "Order1",
            "createDate": "2021-03-16",
            "quantity": "10"
        },
        {
            "orderNo": "Order3",
            "createDate": "2021-03-16",
            "quantity": "30"
        }
    ]
}
```

```
Data Set Up

Availability Picture
Product1-Order1 -2021-03-16,10
Product2-Order2 -2021-03-19,5
Product1-Order3 -2021-03-16,30
Product4-Order4 -2021-03-20,20
Product2-Order5 -2021-03-16,20

```

Logic: Computes the Max/Min Sold out item based upon the user request
