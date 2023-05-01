# afi_parser
parsing software for WOS excel

The Excel file that has been exported from WOS should be saved in the C root as test.xlsx.

The method Obrada should be called. This method parses the Excel and trys to identify authors and institutions. It compares the identities from the Excel file with identities in the database. All new found identities would be stored in database for the further analysis. After this process the outcome would be the Excel testR.xlxR that has results. A row in the Excel contains about author and institution as well as procentage of symilarity with identities in the database.
