Supply Chain Transparency System
Modern supply chains are complex 
raw material suppliers → manufacturers → distributors → warehouses → retailers → customers


1. Problems:
Lack of transparency 
 Customers don’t know where their product came from.
Fraud / Counterfeiting : 
Product Swapping : each product will have a unique id containg its journey, customer can scan that product. If the journey is not the official one it can be marked as tampered
Recalls 
If one faulty batch is found, tracing it back is painful.
2. Use Cases : 
Customer View
Check the full journey of a product (“My laptop was made in Bengaluru, stored in Chennai, shipped via Mumbai, and delivered in Pune”).
Logistics Manager View
See all live shipments.
Detect bottlenecks (e.g., many shipments delayed at one hub).
Supplier / Retailer View
Track inventory flow.
Ensure products are sourced from genuine suppliers.
3. System Requirements : 
Functional 
Add new suppliers, products, warehouses, shipments, transactions
Log every movement of the product (event sourcing)
Track live shipments (current location + status)
Query full journey of a product
Cache hot queries (frequently searched products / shipments)
Analytics : average delivery time, most delayed routes
      2. Non Functional 
Performance : Product journey query should be less than 1 sec
Reliability : no inconsistent product journey (atomic transactions)
Transparency : immutable transaction logs
4. Actors of the System : 
Suppliers : 
One who manufactures the products
Registers themselves in the system
Add new batch of products to the system
Handover products to shipments for delivery to warehouses / retailers
Warehouse : 
Stores products
Receive shipments from suppliers
Send shipments forward to retailers / customers
Retailers : 
Shops, pharmacies or end distributors who sell products to customers
Get shipments from warehouses / Suppliers 
Transactions :
Events or logs that tie everything together
Record every movement of product
5. ER Diagram
A supplier produces many batches of a product
A batch contains many products batches with different suppliers 
A transaction can contain many batches, a batch can be part of many transactions [different warehouse transportation]
A warehouse can contain many batches, a batch can transfer and be part of many warehouses
 Supply Chain ER.drawio
Work Flow : 
Batches : 
Batches are created first as they represent physical products
Created by suppliers through application
After creation -> batch with batch id will be stored in db
Transaction : 
This represents moving batches from location A to B
Created / updated by supply chain operator / logistics manager
Initially from, to location for a transaction is created then singe or multiple batches are added, both Transaction and TransactionBatches tables are updated


ER diagram : https://drive.google.com/file/d/1paHGm0GrdFOI1r-ly5II4W_jASIIh7qL/view?usp=drive_link
