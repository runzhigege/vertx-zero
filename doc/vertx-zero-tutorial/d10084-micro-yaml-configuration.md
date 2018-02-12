# D10084 - Micro, Configuration

From this chapter we'll focus on RPC usage in zero system, because they are micro environment, we need to prepare more service nodes here.

Demo Project and environment

| Http Port | Ipc Port | Ipc Service Name | Project | Role |
| :--- | :--- | :--- | :--- | :--- |
| 6100 |  | None | up-athena | Api Gateway |
| 6201 | 6211 | ipc-common | up-atlas | Common Service |
| 6301 | 6311 | ipc-originator | up-coeus | Originator |
| 6401 | 6411 | ipc-coordinator-a | up-crius | Coordinator A |
| 6402 | 6412 | ipc-coordinator-b | up-hyperion | Coordinator B |
| 6403 | 6413 | ipc-coordinator-c | up-epimetheus | Coordinator C |
| 6501 | 6511 | ipc-terminator | up-cronus | Terminator |

Why we need so many projects ? Because in micro service tutorials we'll focus on Service Registry, Discovery and Communication, we consider use more projects to describe different scenarios and let developers know how to develop micro services in zero framework.

## 1. Etcd Configuration

This tutorial is

