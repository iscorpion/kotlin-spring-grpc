package com.iscorpion.contract.service.grpc

import ContractCreate
import ContractDetails
import ContractServiceGrpc
import customer.service.GetCustomerRequest
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class GrpcServer(
    private val adapter: GrpcAdapter
): ContractServiceGrpc.ContractServiceImplBase() {
    override fun contractDetails(request: GetCustomerRequest, responseObserver: StreamObserver<ContractDetails>): Unit = runBlocking {
        launch {
            val response = adapter.getContractDetails(request)

            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }
    }

    override fun createContract(request: ContractCreate, responseObserver: StreamObserver<ContractDetails>): Unit = runBlocking {
        launch {
            val response = adapter.createContract(request)

            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }
    }
}