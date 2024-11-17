package com.hafidtech.loketbus.ui.model

import com.hafidtech.loketbus.ui.utils.Const
import com.midtrans.sdk.corekit.core.Constants
import com.midtrans.sdk.corekit.core.LocalDataHandler
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.models.BankType
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.UserAddress
import com.midtrans.sdk.corekit.models.UserDetail
import com.midtrans.sdk.corekit.models.snap.Authentication
import com.midtrans.sdk.corekit.models.snap.CreditCard
import java.util.ArrayList
import java.util.UUID
import kotlin.collections.ArrayList

class CustomerPayModel {

    fun userDetails (
        nama : String?,
        email : String?,
        nohp : String?,
        address : String?,
        city : String?,
        codePos : String?,
        country : String?,
    ) {
        var userDetail : UserDetail?
        userDetail = LocalDataHandler.readObject("user_details", UserDetail::class.java)

        if (userDetail == null) {
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
            userAddress.zipcode = codePos
            userAddress.addressType = Constants.ADDRESS_TYPE_BOTH

            userAddresses.add(userAddress)
            userDetail.userAddresses = ArrayList(userAddresses)
            LocalDataHandler.saveObject("user_detail", userDetail)
        }
    }

    companion object {
        fun customerDetails() : CustomerDetails {
            val customerDetails = CustomerDetails()
            customerDetails.firstName = "Desya"
            customerDetails.phone = "62882329156134"
            customerDetails.email = "desya@gmail.com"
            return customerDetails
        }

        fun transactionRequest (
            id : String?,
            price : Int,
            qty : Int,
            name : String?
        ) : TransactionRequest {
            val transactionRequest = TransactionRequest(System.currentTimeMillis().toString()+" ", price.toDouble())
            transactionRequest.customerDetails = customerDetails()
            val details = ItemDetails(id, price.toDouble(), qty, name)
            val itemDetails = ArrayList<ItemDetails>()
            itemDetails.add(details)
            transactionRequest.itemDetails = itemDetails

            val creditCard = CreditCard()
            creditCard.isSaveCard = false
            creditCard.authentication = Authentication.AUTH_RBA
            creditCard.bank = BankType.BCA

            transactionRequest.creditCard = creditCard
            return transactionRequest

        }
    }
}