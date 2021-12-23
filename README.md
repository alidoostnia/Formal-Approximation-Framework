# Formal-Approximation-Framework
Our proposed framework aims at efficiently verifying safety-critical autonomous system models. The need for model-based runtime verification in autonomous system is addressed by the means of formal approximation where the error is under control. The framework makes delay in updating the less important components of the model in favor of the most significant ones. So, the decisions can be made before the deadline, and the safety of the autonomous system is guaranteed. We use two modes for approximation/verification at runtime: major and minor. The minor approximation fulfills the runtime verification requirements. If the minor strategy cannot meet the safety requirements of the autonomous system, the system stpos the main functionalities that may endanger the safety of the system and runs the major approximation mode. After updating the whole model, the system will use the minor approximation again at runtime.

The framework includes three main functions in cooperation with PRISM model checker. The parts including:

1- Partitioning algorithm: it detects the strongly-connected components of the model and divides the model into independent partitions. There is an aggregation mechanism for calculation of final results. 

2- Approximation engine: it approximate each submodel separately using a bisimilar approximation method, namely \varepsilon-approximate probabilistic bismulation. 

3- Scheduling algorithm: it prioritizes and schedules the most significant SCCs for update/re-approximation based on the comming changes. We have used the following project with a few custom changes: https://github.com/elzoughby/EDF-scheduling

4- Verification engine: we use the Prism probabilistic model checker for verification engine. Each submodel that is subject to change, must be verified after updating of the parameters.
