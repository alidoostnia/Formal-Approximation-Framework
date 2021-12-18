# Formal-Approximation-Framework
The framework includes three main functions in cooperation with PRISM model checker. The parts including:

1- Partitioning algorithm: it detects the strongly-connected components of the model and divides the model into independent partitions. There is an aggregation mechanism for calculation of final results. 

2- Approximation engine: it approximate each submodel separately using a bisimilar approximation method, namely \varepsilon-approximate probabilistic bismulation. 

3- Scheduling algorithm: it prioritizes and schedules the most significant SCCs for update/re-approximation based on the comming changes. 

4- Verification engine: we use the Prism probabilistic model checker for verification engine. Each submodel that is subject to change, must be verified after updating of the parameters.
