# Known Issues Report
As mantioned in the SOLID document, some classes aren't 
defy some solid principale mainly Interface segregation
becuase all products Must have an Order and every order 
must support Invoices but not every Product must support invoice.
For example: ProductSoldThroughWebsite dosen't need an invoice directly 
but he is still need to manage shpping orders exclusively, So it still supports invoices.

Also, Every product need to implement getShippingMethod for future proffing.
Should have been in Shippable, but it means problems for factory.

Also, we knew the command pattern is viable for some main functions, but since we don't have
the Map data structrue it wouldn't make sanse since there is no actual abstruction.
