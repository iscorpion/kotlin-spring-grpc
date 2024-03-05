package com.iscorpion.customer.service.grpc

import com.iscorpion.customer.service.adapter.GrpcServiceAdapter
import customer.service.CustomerCreateRequest
import customer.service.CustomerCreateResponse
import customer.service.CustomerServiceGrpc
import customer.service.GetCustomerRequest
import customer.service.GetCustomerResponse
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class CustomerService(
    val adapter: GrpcServiceAdapter
) : CustomerServiceGrpc.CustomerServiceImplBase() {
    override fun getCustomer(request: GetCustomerRequest, responseObserver: StreamObserver<GetCustomerResponse>): Unit =
    runBlocking {
        launch {
            val response = adapter.findCustomer(request.codCustomer)

            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }

    }

    override fun newCustomer(request: CustomerCreateRequest, responseObserver: StreamObserver<CustomerCreateResponse>): Unit =
    runBlocking {
        launch {
            val response = adapter.new(request)
            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }

    }


}

