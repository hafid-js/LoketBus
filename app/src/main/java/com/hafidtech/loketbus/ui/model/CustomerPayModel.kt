package com.hafidtech.loketbus.ui.model

import com.hafidtech.loketbus.ui.handler.Authentication
import com.hafidtech.loketbus.ui.handler.BankType
import com.hafidtech.loketbus.ui.handler.Constants
import com.hafidtech.loketbus.ui.handler.LocalDataHandler
import com.hafidtech.loketbus.ui.handler.UserAddress
import com.hafidtech.loketbus.ui.handler.UserDetail
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.snap.CreditCard
import java.util.UUID

class CustomerPayModel {
    fun userDetails(
        nama : String?,
        email : String?,
        nohp : String?,
        address : String?,
        city : String?,
        codepos : String?,
        country : String?,
    ) {
        var userDetail : UserDetail?
        userDetail = LocalDataHandler.readObject("user_details", UserDetail::class.java)

        if (userDetail == null){
            userDetail = UserDetail()
            userDetail.userFullName = nama
            userDetail.email = email
            userDetail.phoneNumber = nohp
            userDetail.userId = UUID.randomUUID().toString()

            val userAddresses : MutableList<UserAddress> = ArrayList()
            val userAddress = UserAddress()
            userAddress.address = address
            userAddress.city = city
            userAddress.country = country
            userAddress.zipcode = codepos
            userAddress.addressType = Constants.ADDRESS_TYPE_BOTH

            userAddresses.add(userAddress)
            userDetail.userAddresses = ArrayList(userAddresses)
            LocalDataHandler.saveObject("_user_detail", userDetail)
        }
    }

    companion object {
        fun customerDetails() : CustomerDetails {
            val customerDetails = CustomerDetails()
            customerDetails.firstName = "Desya"
            customerDetails.phone = "6285758145631"
            customerDetails.email = "desya@gmail.com"
            return customerDetails
        }

        fun transactonRequest(
            id : String?,
            price : Int,
            qty : Int,
            name : String?,
        ) : TransactionRequest {
            val transactionRequest =
                TransactionRequest(System.currentTimeMillis().toString()+" ", price.toDouble())
            transactionRequest.customerDetails = customerDetails()
            val details = ItemDetails(id, price.toDouble(), qty, name)
            val itemDetails = ArrayList<ItemDetails>()
            itemDetails.add(details)
            transactionRequest.itemDetails = itemDetails

            val creditCard = CreditCard()
            creditCard.isSaveCard = false
            creditCard.authentication = Authentication.AUTH_RBA
            creditCard.bank = BankType.MANDIRI

            transactionRequest.creditCard = creditCard
            return transactionRequest
        }
    }

}