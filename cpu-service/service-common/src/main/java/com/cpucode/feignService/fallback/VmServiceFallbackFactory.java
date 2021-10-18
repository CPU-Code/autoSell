package com.cpucode.feignService.fallback;

import com.cpucode.feignService.VMService;
import com.cpucode.viewmodel.RegionViewModel;
import com.cpucode.viewmodel.SkuViewModel;
import com.cpucode.viewmodel.VendingMachineViewModel;
import com.google.common.collect.Lists;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : cpucode
 * @date : 2021/10/18 17:00
 * @github : https://github.com/CPU-Code
 * @csdn : https://blog.csdn.net/qq_44226094
 */
@Component
@Slf4j
public class VmServiceFallbackFactory implements FallbackFactory<VMService> {
    @Override
    public VMService create(Throwable throwable) {
        log.error("调用售货机服务失败",throwable);

        return new VMService() {
            @Override
            public Integer getNodeCountByOwnerId(Integer ownerId) {
                return null;
            }

            @Override
            public Integer getVmCountByOwnerId(Integer ownerId) {
                return null;
            }

            @Override
            public VendingMachineViewModel getVMInfo(String innerCode) {
                return null;
            }

            @Override
            public void inventory(int percent) {

            }

            @Override
            public List<SkuViewModel> getAllSkuByInnerCode(String innerCode) {
                return Lists.newArrayList();
            }

            @Override
            public SkuViewModel getSku(String innerCode, String skuId) {
                return null;
            }

            @Override
            public SkuViewModel getSkuById(long skuId) {
                return null;
            }

            @Override
            public RegionViewModel getRegionById(String regionId) {
                RegionViewModel viewModel = new RegionViewModel();

                return viewModel;
            }

            @Override
            public String getNodeName(Long id) {
                return null;
            }
        };
    }
}
