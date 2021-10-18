package com.cpucode.feignService;

import com.cpucode.feignService.fallback.VmServiceFallbackFactory;
import com.cpucode.viewmodel.RegionViewModel;
import com.cpucode.viewmodel.SkuViewModel;
import com.cpucode.viewmodel.VendingMachineViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/18 16:58
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@FeignClient(value = "vm-service", fallbackFactory = VmServiceFallbackFactory.class)
public interface VMService {
    @GetMapping("/node/countForOwner/{ownerId}")
    Integer getNodeCountByOwnerId(@PathVariable("ownerId") Integer ownerId);

    @GetMapping("/vm/countByOwner/{ownerId}")
    Integer getVmCountByOwnerId(@PathVariable("ownerId") Integer ownerId);

    @GetMapping("/vm/info/{innerCode}")
    VendingMachineViewModel getVMInfo(@PathVariable String innerCode);

    @GetMapping("/vm/inventory/{percent}")
    void inventory(@PathVariable int percent);

    @GetMapping("/vm/skuList/{innerCode}")
    List<SkuViewModel> getAllSkuByInnerCode(@PathVariable String innerCode);

    @GetMapping("/vm/sku/{innerCode}/{skuId}")
    SkuViewModel getSku(@PathVariable String innerCode,@PathVariable String skuId);

    @GetMapping("/sku/skuViewModel/{skuId}")
    SkuViewModel getSkuById(@PathVariable long skuId);

    @GetMapping("/region/regionInfo/{regionId}")
    RegionViewModel getRegionById(@PathVariable String regionId);

    @GetMapping("/node/nodeName/{id}")
    String getNodeName(@PathVariable Long id);
}
